#ifndef TEMPERATURE_H
#define TEMPERATURE_H

#include "types.h"
#include <string>

enum class TemperatureUnit
{
  Celsius,
  Fahrenheit,
  Kelvin
};

namespace TemperatureConversion
{
  void startFlow();
  double getSourceValue();
  TemperatureUnit getTemperatureUnit(const std::string_view &sourceOrTarget);
  double convertTemperature(double value, TemperatureUnit from, TemperatureUnit to);
}

#endif
