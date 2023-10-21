package com.maria;

import java.time.LocalDate;
import java.util.Objects;

public class Worker {
    private long worker_id;
    private String wname;
    private LocalDate birthday;
    private WLevel wlevel;
    private int salary;
    public enum WLevel {
        Trainee,
        Junior,
        Middle,
        Senior
    }

    public long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(long worker_id) {
        this.worker_id = worker_id;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public WLevel getWlevel() {
        return wlevel;
    }

    public void setWlevel(WLevel wlevel) {
        this.wlevel = wlevel;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return worker_id == worker.worker_id && salary == worker.salary && Objects.equals(wname, worker.wname) && Objects.equals(birthday, worker.birthday) && wlevel == worker.wlevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker_id, wname, birthday, wlevel, salary);
    }
}
