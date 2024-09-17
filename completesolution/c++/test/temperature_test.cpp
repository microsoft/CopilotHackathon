#include "temperature.h"
#include <gtest/gtest.h>

TEST(TemperatureConverterTest, FahrenheitToCelsius) {
  double result = TemperatureConversion::convertTemperature(32, TemperatureUnit::Fahrenheit, TemperatureUnit::Celsius);
  ASSERT_DOUBLE_EQ(result, 0);
}

TEST(TemperatureConverterTest, KelvinToCelsius) {
  double result = TemperatureConversion::convertTemperature(273.15, TemperatureUnit::Kelvin, TemperatureUnit::Celsius);
  ASSERT_DOUBLE_EQ(result, 0);
}

TEST(TemperatureConverterTest, CelsiusToFahrenheit) {
  double result = TemperatureConversion::convertTemperature(0, TemperatureUnit::Celsius, TemperatureUnit::Fahrenheit);
  ASSERT_DOUBLE_EQ(result, 32);
}

TEST(TemperatureConverterTest, CelsiusToKelvin) {
  double result = TemperatureConversion::convertTemperature(0, TemperatureUnit::Celsius, TemperatureUnit::Kelvin);
  ASSERT_DOUBLE_EQ(result, 273.15);
}

TEST(TemperatureConverterTest, FahrenheitToKelvin) {
  double result = TemperatureConversion::convertTemperature(32, TemperatureUnit::Fahrenheit, TemperatureUnit::Kelvin);
  ASSERT_DOUBLE_EQ(result, 273.15);
}

TEST(TemperatureConverterTest, KelvinToFahrenheit) {
  double result = TemperatureConversion::convertTemperature(273.15, TemperatureUnit::Kelvin, TemperatureUnit::Fahrenheit);
  ASSERT_DOUBLE_EQ(result, 32);
}
