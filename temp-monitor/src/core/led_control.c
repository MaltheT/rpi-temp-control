#include <stdio.h>
#include <temp-monitor/core/led_control.h>

void turnOffLed(void) {
  FILE *ledfile = fopen("/sys/class/leds/ACT/brightness", "w");

  if (!ledfile) {
    printf("error reading the LED file\n");
    return;
  }

  fprintf(ledfile, "1");
  fflush(ledfile);
  fclose(ledfile);
}

void turnOnLed(void) {
  FILE *ledfile = fopen("/sys/class/leds/ACT/brightness", "w");

  if (!ledfile) {
    printf("error reading the LED file\n");
    return;
  }

  fprintf(ledfile, "0");
  fflush(ledfile);
  fclose(ledfile);
}
