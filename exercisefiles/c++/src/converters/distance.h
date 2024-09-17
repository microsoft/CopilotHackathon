#ifndef DISTANCE_H
#define DISTANCE_H

#include <string>

enum class DistanceUnit
{
  Meters,
  Feet,
  Yards
};

namespace DistanceConversion
{
  void startFlow();
  double getSourceValue();
  DistanceUnit getDistanceUnit(const std::string_view &sourceOrTarget);
  double convertDistance(double value, DistanceUnit from, DistanceUnit to);
}

#endif
