#include "gtest/gtest.h"
#include "distance.h"

TEST(DistanceConversionTest, MetersToFeet) {
  double result = DistanceConversion::convertDistance(1.0, DistanceUnit::Meters, DistanceUnit::Feet);
  ASSERT_NEAR(result, 3.28084, 0.00001);
}

TEST(DistanceConversionTest, FeetToMeters) {
  double result = DistanceConversion::convertDistance(3.28084, DistanceUnit::Feet, DistanceUnit::Meters);
  ASSERT_NEAR(result, 1.0, 0.00001);
}

TEST(DistanceConversionTest, MetersToYards) {
  double result = DistanceConversion::convertDistance(1.0, DistanceUnit::Meters, DistanceUnit::Yards);
  ASSERT_NEAR(result, 1.09361, 0.00001);
}

TEST(DistanceConversionTest, YardsToMeters) {
  double result = DistanceConversion::convertDistance(1.09361, DistanceUnit::Yards, DistanceUnit::Meters);
  ASSERT_NEAR(result, 1.0, 0.00001);
}

TEST(DistanceConversionTest, FeetToYards) {
  double result = DistanceConversion::convertDistance(3.0, DistanceUnit::Feet, DistanceUnit::Yards);
  ASSERT_NEAR(result, 1.0, 0.00001);
}

TEST(DistanceConversionTest, YardsToFeet) {
  double result = DistanceConversion::convertDistance(1.0, DistanceUnit::Yards, DistanceUnit::Feet);
  ASSERT_NEAR(result, 3.0, 0.00001);
}