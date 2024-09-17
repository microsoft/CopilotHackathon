#include "temperature.h"
#include <cstdio>
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

    printf("%.2f%s is %.2f%s\n", sourceValue, getTemperatureUnitSign(from).c_str(), targetValue, getTemperatureUnitSign(to).c_str());
  }

  double getSourceValue()
  {
    double value;
    printf("Enter the value to be converted: ");
    scanf("%lf", &value);
    return value;
  }

  TemperatureUnit getTemperatureUnit(const std::string_view &sourceOrTarget)
  {
    int choice;
    printf("Select %s temperature unit:\n", std::string(sourceOrTarget).c_str());
    printf("[1] Celsius\n");
    printf("[2] Fahrenheit\n");
    printf("[3] Kelvin\n");
    printf("Enter choice: ");
    scanf("%d", &choice);

    switch (choice)
    {
    case 1:
      return TemperatureUnit::Celsius;
    case 2:
      return TemperatureUnit::Fahrenheit;
    case 3:
      return TemperatureUnit::Kelvin;
    default:
      return TemperatureUnit::Celsius;
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
