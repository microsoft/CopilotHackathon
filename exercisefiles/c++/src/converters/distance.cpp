#include "distance.h"
#include <cstdio>
#include <unordered_map>

namespace /* private */ {
  std::string getDistanceUnitSign(DistanceUnit unit)
  {
    std::unordered_map<DistanceUnit, std::string> unitSigns = {
      {DistanceUnit::Meters, "m"},
      {DistanceUnit::Feet, "ft"},
      {DistanceUnit::Yards, "yd"}
    };

    return unitSigns.at(unit);
  }
} // namespace private

namespace DistanceConversion
{
  void startFlow()
  {
    double sourceValue = getSourceValue();
    DistanceUnit from = getDistanceUnit("source");
    DistanceUnit to = getDistanceUnit("target");

    double targetValue = convertDistance(sourceValue, from, to);

    printf("%.2f%s is %.2f%s\n", sourceValue, getDistanceUnitSign(from).c_str(), targetValue, getDistanceUnitSign(to).c_str());
  }

  double getSourceValue()
  {
    double value;
    printf("Enter the value to be converted: ");
    scanf("%lf", &value);
    return value;
  }

  DistanceUnit getDistanceUnit(const std::string_view &sourceOrTarget)
  {
    int choice;
    printf("Select %s distance unit:\n", std::string(sourceOrTarget).c_str());
    printf("[1] Meters\n");
    printf("[2] Feet\n");
    printf("[3] Yards\n");
    printf("Enter choice: ");
    scanf("%d", &choice);

    switch (choice)
    {
    case 1:
      return DistanceUnit::Meters;
    case 2:
      return DistanceUnit::Feet;
    case 3:
      return DistanceUnit::Yards;
    default:
      return DistanceUnit::Meters; // Default to Meters
    }
  }

  double convertDistance(double value, DistanceUnit from, DistanceUnit to)
  {
    // Todo implement the actual conversion logic
    return 0;
  }
}
