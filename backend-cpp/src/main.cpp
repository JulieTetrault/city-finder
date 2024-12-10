#include "app.h"
#include "CsvParser.h"
#include "model/City.h"

#include <cpprest/http_listener.h>
#include <cpprest/json.h>
#include <cpprest/asyncrt_utils.h>

#include <fstream>
#include <iostream>
#include <vector>

using namespace web;
using namespace web::http;
using namespace web::http::experimental::listener;

std::string getStringQueryParam(http_request request, std::string queryParamName){
    auto queryParams = uri::split_query(request.request_uri().query());
    auto queryParam = queryParams.find(U(queryParamName));
    if(queryParam->first != queryParamName){
        return "";
    }
    utility::string_t decodedParam = uri::decode(queryParam->second);
    return utility::conversions::to_utf8string(decodedParam);
}

int* getIntQueryParam(http_request request, std::string queryParamName){
    std::string stringQueryParam = getStringQueryParam(request, queryParamName);
    try {
        return new int(std::stoi(stringQueryParam));
    } catch(const std::invalid_argument&){
        return nullptr;
    }
}

double* getDoubleQueryParam(http_request request, std::string queryParamName){
    std::string stringQueryParam = getStringQueryParam(request, queryParamName);
    try {
        return new double(std::stod(stringQueryParam));
    } catch(const std::invalid_argument&){
        return nullptr;
    }
}

void handleGet(http_request request)
{
    http_response response(status_codes::OK);
    response.headers().add(U("Access-Control-Allow-Origin"), U("http://localhost:3000"));
    response.set_body(getResults(getStringQueryParam(request, "q"), getIntQueryParam(request, "page"), getDoubleQueryParam(request, "latitude"), getDoubleQueryParam(request, "longitude")));

    request.reply(response);

    utility::string_t uri_string = request.request_uri().to_string().c_str();

    std::cout << "Received request for URI: " << utility::conversions::to_utf8string(uri_string) << std::endl;
}

int main()
{
    try
    {
        uri_builder uri(U("http://0.0.0.0:8080"));
        uri.append_path(U("suggestions"));

        auto addr = uri.to_uri().to_string();
        http_listener listener(addr);

        listener.support(methods::GET, handleGet);

        listener
            .open()
            .then([&listener]() {
                std::cout << "Listening at: " << utility::conversions::to_utf8string(listener.uri().to_string()) << std::endl;
            })
            .wait();

        // Keep the service running
        std::string line;
        std::getline(std::cin, line);

        listener.close().wait();
    }
    catch (const std::exception& e)
    {
        std::cerr << "Error: " << e.what() << std::endl;
    }

    return 0;
}
