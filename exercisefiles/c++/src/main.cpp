#include "converters/types.h"
#include "converters/temperature.h"
#include <iostream>
#include <array>

ConversionType getConversionType()
{
  int choice;
  std::array<ConversionType, 2> conversionTypes = {
      ConversionType::Temperature,
      ConversionType::Distance};

  while (true)
  {
    printf("Select type of conversion:\n");
    printf("[1] Temperature\n");
    printf("Enter choice: ");
    scanf("%d", &choice);

    if (choice > 0 && choice <= conversionTypes.size())
    {
      return conversionTypes[choice - 1];
    }

    printf("Invalid choice. Please try again.\n");
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
  }
}
