from gpiozero import LED  # imports the LED functions from gpiozero library

from time import sleep  # imports the sleep function from time library

# LED names according to artifacts
artifact1 = LED(17)  # declared gpio pin 17 for LED and store it in artifact1
artifact2 = LED(23)
artifact3 = LED(18)
artifact4 = LED(22)


def turnOnAllLeds():
    artifact2.on()
    artifact1.on()
    artifact3.on()
    artifact4.on()


def turnOffAllLeds():
    artifact2.off()
    artifact1.off()
    artifact3.off()
    artifact4.off()


def controlArtifact(led_name, on_off):
    if led_name == "1":
        if on_off:
            artifact1.on()  # turn on artifact1
            print("artifact2 turned on!")
        if not on_off:
            artifact1.off()
    elif led_name == "2":
        if on_off:
            artifact2.on()  # turn on artifact1
            print("artifact1 turned on!")
        if not on_off:
            artifact2.off()
    elif led_name == "3":
        if on_off:
            artifact3.on()  # turn on artifact1
            print("artifact1 turned on!")
        if not on_off:
            artifact3.off()
    elif led_name == "4":
        if on_off:
            artifact4.on()  # turn on artifact1
            print("artifact1 turned on!")
        if not on_off:
            artifact4.off()
