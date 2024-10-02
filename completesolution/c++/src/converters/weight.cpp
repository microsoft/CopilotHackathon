#include "weight.h"
#include <iostream>
#include <unordered_map>

namespace /* private */
{
  std::string getWeightUnitSign(WeightConversion::WeightUnit unit)
  {
    std::unordered_map<WeightConversion::WeightUnit, std::string> unitSigns = {
      {WeightConversion::WeightUnit::Kilograms, "kg"},
      {WeightConversion::WeightUnit::Pounds, "lb"},
      {WeightConversion::WeightUnit::Ounces, "oz"}
    };

    return unitSigns.at(unit);
  }
} // namespace private

namespace WeightConversion
{
  void startFlow()
  {
    double sourceValue = getSourceValue();
    WeightUnit from = getWeightUnit("source");
    WeightUnit to = getWeightUnit("target");

    double targetValue = convertWeight(sourceValue, from, to);

    std::cout << sourceValue << getWeightUnitSign(from) << " is " << targetValue << getWeightUnitSign(to) << std::endl;
  }

  double getSourceValue()
  {
    double value;
    std::cout << "Enter the value to be converted: ";
    std::cin >> value;
    return value;
  }

  WeightUnit getWeightUnit(const std::string_view &sourceOrTarget)
  {
    int choice;
    std::cout << "Select " << sourceOrTarget << " weight unit:\n";
    std::cout << "[1] Kilograms\n";
    std::cout << "[2] Pounds\n";
    std::cout << "[3] Ounces\n";
    std::cout << "Enter choice: ";
    std::cin >> choice;

    switch (choice)
    {
    case 1:
      return WeightUnit::Kilograms;
    case 2:
      return WeightUnit::Pounds;
    case 3:
      return WeightUnit::Ounces;
    default:
      return WeightUnit::Kilograms; // Default to Kilograms
    }
  }

  double convertWeight(double value, WeightUnit from, WeightUnit to)
  {
    if (from == to)
    {
      return value;
    }

    // Convert everything to Kilograms first
    if (from == WeightUnit::Pounds)
    {
      value = value * 0.453592;
    }
    else if (from == WeightUnit::Ounces)
    {
      value = value * 0.0283495;
    }

    // Then convert to the target unit
    if (to == WeightUnit::Pounds)
    {
      value = value / 0.453592;
    }
    else if (to == WeightUnit::Ounces)
    {
      value = value / 0.0283495;
    }

    return value;
  }
}