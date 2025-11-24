package calendar

import (
	"errors"
	"fmt"
)

type Date struct {
	year  int
	month int
	day   int
}

func welcome() {
	fmt.Println("Welcome") //undxported
}
func (d *Date) Year() int {
	welcome() //소문자로 시작하기 때문에 내부에서만 접근 가능
	return d.year
}
func (d *Date) Month() int {
	return d.month
}
func (d *Date) Day() int {
	return d.day
}
func (d *Date) SetYear(year int) error {
	if year < 1 {
		return errors.New("invalid year")
	}
	d.year = year
	return nil
}
func (d *Date) SetMonth(month int) error {
	if month < 1 || month > 12 {
		return errors.New("invalid month")
	}
	d.month = month
	return nil
}
func (d *Date) SetDay(day int) error {
	if day < 1 || day > 31 {
		return errors.New("invalid day")
	}
	d.day = day
	return nil
}
