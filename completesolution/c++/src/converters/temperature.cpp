#include "temperature.h"
#include <iostream>
#include <unordered_map>

namespace /* private */
{
  std::string getTemperatureUnitSign(TemperatureUnit unit)
  {
    std::unordered_map<TemperatureUnit, std::string> unitSigns = {
      {TemperatureUnit::Celsius, "°C"},
      {TemperatureUnit::Fahrenheit, "°F"},
      {TemperatureUnit::Kelvin, "K"}
    };

    return unitSigns.at(unit);
  }
} // namespace private

namespace TemperatureConversion
{
  void startFlow()
  {
    double sourceValue = getSourceValue();
    TemperatureUnit from = getTemperatureUnit("source");
    TemperatureUnit to = getTemperatureUnit("target");

    double targetValue = convertTemperature(sourceValue, from, to);

    std::cout << sourceValue << getTemperatureUnitSign(from) << " is " << targetValue << getTemperatureUnitSign(to) << std::endl;
  }

  double getSourceValue()
  {
    double value;
    std::cout << "Enter the value to be converted: ";
    std::cin >> value;
    return value;
  }

  TemperatureUnit getTemperatureUnit(const std::string_view &sourceOrTarget)
  {
    int choice;
    std::cout << "Select " << sourceOrTarget << " temperature unit:\n";
    std::cout << "[1] Celsius\n";
    std::cout << "[2] Fahrenheit\n";
    std::cout << "[3] Kelvin\n";
    std::cout << "Enter choice: ";
    std::cin >> choice;

    switch (choice)
    {
    case 1:
      return TemperatureUnit::Celsius;
    case 2:
      return TemperatureUnit::Fahrenheit;
    case 3:
      return TemperatureUnit::Kelvin;
    default:
      return TemperatureUnit::Celsius; // Default to Celsius
    }
  }

  double convertTemperature(double value, TemperatureUnit from, TemperatureUnit to)
  {
    if (from == to)
    {
      return value;
    }

    // Convert everything to Celsius first
    if (from == TemperatureUnit::Fahrenheit)
    {
      value = (value - 32) * 5 / 9;
    }
    else if (from == TemperatureUnit::Kelvin)
    {
      value -= 273.15;
    }

    // Then convert to the target unit
    if (to == TemperatureUnit::Fahrenheit)
    {
      value = value * 9 / 5 + 32;
    }
    else if (to == TemperatureUnit::Kelvin)
    {
      value += 273.15;
    }

    return value;
  }
}