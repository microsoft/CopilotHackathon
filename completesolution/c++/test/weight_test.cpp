#include <gtest/gtest.h>
#include "weight.h"

TEST(WeightConversionTest, KilogramsToPounds) {
  double result = WeightConversion::convertWeight(1.0, WeightConversion::WeightUnit::Kilograms, WeightConversion::WeightUnit::Pounds);
  ASSERT_NEAR(result, 2.20462, 0.00001);
}

TEST(WeightConversionTest, PoundsToKilograms) {
  double result = WeightConversion::convertWeight(2.20462, WeightConversion::WeightUnit::Pounds, WeightConversion::WeightUnit::Kilograms);
  ASSERT_NEAR(result, 1.0, 0.00001);
}

TEST(WeightConversionTest, KilogramsToOunces) {
  double result = WeightConversion::convertWeight(1.0, WeightConversion::WeightUnit::Kilograms, WeightConversion::WeightUnit::Ounces);
  ASSERT_NEAR(result, 35.274, 0.001);
}

TEST(WeightConversionTest, OuncesToKilograms) {
  double result = WeightConversion::convertWeight(35.274, WeightConversion::WeightUnit::Ounces, WeightConversion::WeightUnit::Kilograms);
  ASSERT_NEAR(result, 1.0, 0.001);
}

TEST(WeightConversionTest, PoundsToOunces) {
  double result = WeightConversion::convertWeight(1.0, WeightConversion::WeightUnit::Pounds, WeightConversion::WeightUnit::Ounces);
  ASSERT_NEAR(result, 16.0, 0.00001);
}

TEST(WeightConversionTest, OuncesToPounds) {
  double result = WeightConversion::convertWeight(16.0, WeightConversion::WeightUnit::Ounces, WeightConversion::WeightUnit::Pounds);
  ASSERT_NEAR(result, 1.0, 0.00001);
}