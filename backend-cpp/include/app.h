#ifndef APP_H
#define APP_H

#include <cpprest/json.h>
using namespace web;

json::value getResults(const std::string& q, int* page, const double* latitude = new double(45.9778182), const double* longitude = new double(-77.8968753));

#endif
