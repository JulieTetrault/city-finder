#ifndef CSV_PARSER_H
#define CSV_PARSER_H

#include "model/City.h"
#include <fstream>
#include <sstream>
#include <vector>
#include <iostream>

class CsvParser {
public:
    CsvParser(const CsvParser&) = delete;
    CsvParser& operator=(const CsvParser&) = delete;

    static CsvParser& getInstance()
    {
        static CsvParser instance;
        return instance;
    }

    std::vector<City> readCities(std::ifstream& file)
    {
        std::vector<City> cities;
        // Pre-allocate to avoid dynamic allocations
        cities.resize(1000, City());

        if (!file.is_open()) {
            std::cerr << "Error: Could not open the file." << std::endl;
            return cities;
        }

        std::string line;

        std::getline(file, line); // header line

        while (std::getline(file, line)) {
            std::stringstream ss(line);
            std::string idStr;
            std::string name;
            std::string ascii;
            std::string alt_name;
            std::string latitudeStr;
            std::string longitudeStr;
            std::string feat_class;
            std::string feat_code;
            std::string country;
            std::string cc2;
            std::string admin1;
            std::string admin2;
            std::string admin3;
            std::string admin4;
            std::string populationStr;
            std::string elevationStr;
            std::string dem;
            std::string tz;
            std::string modified_at;

            if (std::getline(ss, idStr, '\t') &&
                std::getline(ss, name, '\t') &&
                std::getline(ss, ascii, '\t') &&
                std::getline(ss, alt_name, '\t') &&
                std::getline(ss, latitudeStr, '\t') &&
                std::getline(ss, longitudeStr, '\t') &&
                std::getline(ss, feat_class, '\t') &&
                std::getline(ss, feat_code, '\t') &&
                std::getline(ss, country, '\t') &&
                std::getline(ss, cc2, '\t') &&
                std::getline(ss, admin1, '\t') &&
                std::getline(ss, admin2, '\t') &&
                std::getline(ss, admin3, '\t') &&
                std::getline(ss, admin4, '\t') &&
                std::getline(ss, populationStr, '\t') &&
                std::getline(ss, elevationStr, '\t') &&
                std::getline(ss, dem, '\t') &&
                std::getline(ss, tz, '\t') &&
                std::getline(ss, modified_at, '\t')
                ) {
                int id = std::stoi(idStr);
                float latitude = std::stof(latitudeStr);
                float longitude = std::stof(longitudeStr);;
                int population = std::stoi(populationStr);
                int elevation;
                try{
                    elevation = std::stoi(elevationStr);
                } catch(const std::invalid_argument&){
                    elevation = -1;
                }
                cities.emplace_back(id, name, ascii, alt_name, latitude, longitude, country, admin1, population, elevation, tz, modified_at, feat_class, feat_code, cc2, dem, admin2, admin3, admin4);
            }
        }

        return cities;
    }


private:
    CsvParser() {}

    ~CsvParser() {}
};

#endif
