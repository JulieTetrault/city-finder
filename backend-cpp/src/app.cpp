#include "app.h"
#include "CsvParser.h"
#include "model/City.h"

#include <cpprest/json.h>

#include <fstream>
#include <iostream>
#include <vector>

using namespace web;

json::value vectorToJsonArray(const std::vector<City>& cities_vector = *(static_cast<const std::vector<City>*>(nullptr)))
{
    json::value json_array = json::value::array();
    if(&cities_vector == nullptr) {
        return json_array;
    }

    for (int i = 0; i < cities_vector.size(); ++i) {
        json_array[i] = cities_vector[i].toJson();
    }

    return json_array;
}

template <typename T, typename Predicate>
void removeIfInplace(std::vector<T>& vec, Predicate predicate) {
    vec.erase(std::remove_if(vec.begin(), vec.end(), predicate), vec.end());
}

json::value getResults(const std::string& q, int* page, const double* latitude, const double* longitude) {
    json::value results = json::value::object();

    std::string filename = "cities_canada-usa.tsv";
    std::ifstream file;
    file.open(filename);
    if (!file.is_open()) {
        std::cerr << "Error: Could not open the file " << filename << std::endl;
        return json::value::null();
    }

    std::vector<City> cities = CsvParser::getInstance().readCities(file);

    if (!q.empty()) {
        std::cout << "Filtering cities with name '" << q << "'." << std::endl;
        removeIfInplace(cities, [q](City& c) {
            return c.name.find(q) == std::string::npos;
        });
    }
    if (latitude != nullptr) {
        std::cout << "Filtering cities by latitude." << std::endl;
        removeIfInplace(cities, [latitude](City& c) {
            return std::abs(c.latitude - *latitude) > 10.0;
        });
    }
    if (longitude != nullptr) {
        std::cout << "Filtering cities by longitude." << std::endl;
        removeIfInplace(cities, [longitude](City& c) {
            return std::abs(c.longitude - *longitude) > 20.0;
        });
    }

    if (page != nullptr) {
        std::cout << "Page is '" << *page << "'." << std::endl;
        results[U("page")] = json::value::number(*page);
        int totalNumberOfPages = cities.size() % 5 == 0 ? cities.size() / 5 : (cities.size() / 5) + 1;
        results[U("totalNumberOfPages")] = json::value::number(totalNumberOfPages);
        if(*page < totalNumberOfPages) {
            cities = std::vector<City>(cities.begin() + (*page * 5), cities.begin() + ((*page * 5 + 5) >= cities.size() ? cities.size() : *page * 5 + 5));
        } else {
            cities = std::vector<City>();
        }
    }

    results[U("cities")] = vectorToJsonArray(cities);

    return results;
}
