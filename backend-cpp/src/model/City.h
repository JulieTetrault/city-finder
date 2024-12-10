#ifndef CITY_H
#define CITY_H

#include <cpprest/json.h>

using namespace web;

class City {
public:
    int id;
    std::string name;
    std::string ascii;
    std::string alt_name;
    float latitude;
    float longitude;
    std::string country;
    std::string admin1;
    int population;
    int elevation;
    std::string tz;
    std::string modified_at;
    std::string feat_class;
    std::string feat_code;

    // Un-documented attributes
    std::string cc2;
    std::string dem;
    std::string admin2;
    std::string admin3;
    std::string admin4;

    City(){};

    City(const int& id,
         const std::string& name,
         const std::string& ascii,
         const std::string& alt_name,
         const float& latitude,
         const float& longitude,
         const std::string& country,
         const std::string& admin1,
         const int& population,
         const int& elevation,
         const std::string& tz,
         const std::string& modified_at,
         const std::string& feat_class,
         const std::string& feat_code,
         const std::string& cc2,
         const std::string& dem,
         const std::string& admin2,
         const std::string& admin3,
         const std::string& admin4
         ) : id(id),
             name(name),
             ascii(ascii),
             alt_name(alt_name),
             latitude(latitude),
             longitude(longitude),
             country(country),
             admin1(admin1),
             population(population),
             elevation(elevation),
             tz(tz),
             modified_at(modified_at),
             feat_class(feat_class),
             feat_code(feat_code),
             cc2(cc2),
             dem(dem),
             admin2(admin2),
             admin3(admin3),
             admin4(admin4)
        {}

    json::value toJson() const {
        json::value json_value = json::value::object();

        json_value[U("id")] = json::value::number(id);
        json_value[U("name")] = json::value::string(name);
        json_value[U("ascii")] = json::value::string(ascii);
        json_value[U("alt_name")] = json::value::string(alt_name);
        json_value[U("latitude")] = json::value::number(latitude);
        json_value[U("longitude")] = json::value::number(longitude);
        json_value[U("country")] = json::value::string(country);
        json_value[U("admin1")] = json::value::string(admin1);
        json_value[U("population")] = json::value::number(population);
        json_value[U("elevation")] = json::value::number(elevation);
        json_value[U("tz")] = json::value::string(tz);
        json_value[U("modified_at")] = json::value::string(modified_at);
        json_value[U("feat_class")] = json::value::string(feat_class);
        json_value[U("feat_code")] = json::value::string(feat_code);
        json_value[U("cc2")] = json::value::string(cc2);
        json_value[U("dem")] = json::value::string(dem);
        json_value[U("admin2")] = json::value::string(admin2);
        json_value[U("admin3")] = json::value::string(admin3);
        json_value[U("admin4")] = json::value::string(admin4);

        return json_value;
    }
};

#endif // CITY_H
