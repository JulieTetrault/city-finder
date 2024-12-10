#include <gtest/gtest.h>
#include <cpprest/json.h>

#include "app.h"

using namespace web;

// Google Test fixture
class main : public ::testing::Test {
protected:
    void SetUp() override {
    }
    void TearDown() override {
    }
};

TEST(main, test1) {
    json::value value = getResults("test", 0);

    ASSERT_TRUE(value.has_array_field("cities"));
    ASSERT_EQ(value["cities"].as_array().size(), 0);
}

TEST(main, test2) {
    json::value value = getResults("Qué", 0);

    ASSERT_TRUE(value.has_array_field("cities"));
    ASSERT_GT(value["cities"].as_array().size(), 0);
}

TEST(main, test3) {
    json::value value = getResults("Qué", 0);

    ASSERT_TRUE(value.has_array_field("cities"));
    ASSERT_EQ(value["cities"].as_array().at(0)[U("name")].as_string(),U("Québec"));
}
