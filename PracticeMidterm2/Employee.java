public class Employee extends Person {
    private final int hourlyRate;

    public Employee(String name, int age, int hourlyRate) {
        super(name, age);

        if (hourlyRate < 0) {
            throw new IllegalArgumentException();
        } else {
            this.hourlyRate = hourlyRate;
        }
    }

    public Employee(Employee employee) {
        super(employee);
        this.hourlyRate = employee.getHourlyRate();
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int calculateIncome(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException();
        }

        return hourlyRate * hours;

    }

    public boolean equals(Object object) {
        if (object instanceof Employee) {
            Employee employee = (Employee) object;
            if (employee.getAge() == this.getAge() && 
                employee.getName() == this.getName() &&
                employee.getHourlyRate() == this.getHourlyRate()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return String.format("Employee<name=%s, age=%d, hourlyRate=%d", 
            this.getName(), 
            this.getAge(), 
            this.getHourlyRate());
    }
}
