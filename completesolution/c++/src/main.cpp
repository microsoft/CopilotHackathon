#include "converters/types.h"
#include "converters/temperature.h"
#include "converters/distance.h"
#include "converters/weight.h"
#include <iostream>
#include <array>

ConversionType getConversionType()
{
  int choice;
  std::array<ConversionType, 3> conversionTypes = {
      ConversionType::Temperature,
      ConversionType::Distance,
      ConversionType::Weight};

  while (true)
  {
    std::cout << "Select type of conversion:\n";
    std::cout << "[1] Temperature\n";
    std::cout << "[2] Distance\n";
    std::cout << "[3] Weight\n";
    std::cout << "Enter choice: ";
    std::cin >> choice;

    if (choice > 0 && choice <= conversionTypes.size())
    {
      return conversionTypes[choice - 1];
    }

    std::cout << "Invalid choice. Please try again.\n";
  }
}

int main()
{
  ConversionType type = getConversionType();
  switch (type)
  {
  case ConversionType::Temperature:
    {
      TemperatureConversion::startFlow();
      break;
    }
  case ConversionType::Distance:
    {
      DistanceConversion::startFlow();
      break;
    }
  case ConversionType::Weight:
    {
      WeightConversion::startFlow();
      break;
    }
  }
}