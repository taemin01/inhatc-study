package main

import "fmt"

type Meters float64
type KiloMeters float64
type Miles float64

func (l Meters) MeterToMiles() Miles {
	return Miles(l * 0.621371)
}
func (m KiloMeters) KiloMeterToMiles() Miles {
	return Miles(m * 0.000621371)
}

func main() {
	kmph := KiloMeters(2) //receiver parameter
	fmt.Printf("%0.3f Kilometer per hour %0.3f Mile per hour\n", kmph, kmph.KiloMeterToMiles())
	meter := Meters(500)
	fmt.Printf("%0.3f Meter %0.3f Mile \n", meter, meter.MeterToMiles())
}
