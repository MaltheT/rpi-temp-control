#include "temp-monitor/core/control_loop.h"
int main(int argc, char *argv[]) {
  ControlLoop ctrlLoop(100);
  ctrlLoop.setTempThreshold(31.0);
  ctrlLoop.startLoop();
  return 0;
}
