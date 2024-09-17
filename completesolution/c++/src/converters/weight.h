#include <string_view>

#ifndef WEIGHT_H
#define WEIGHT_H

namespace WeightConversion
{
  enum class WeightUnit
  {
    Kilograms,
    Pounds,
    Ounces
  };

  void startFlow();
  double getSourceValue();
  WeightUnit getWeightUnit(const std::string_view &sourceOrTarget);
  double convertWeight(double value, WeightUnit from, WeightUnit to);
}

#endif // WEIGHT_H