package org.howard.edu.lsp.midterm.question4;

public class Camera extends Device implements Networked, BatteryPowered {
    private int batteryPercent;

    public Camera(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
    }

    // Networked interface implementation
    @Override
    public void connect() {
        setConnected(true);
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    // BatteryPowered interface implementation
    @Override
    public int getBatteryPercent() {
        return batteryPercent;
    }

    @Override
    public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("battery 0..100");
        }
        this.batteryPercent = percent;
    }

    // Device abstract method implementation
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Camera[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}

/*
 * Brief Rationale:
 * 
 * Why is Device defined as an abstract class?
 * Device is abstract because it provides common state and behavior (id, location, heartbeat, 
 * connection status) that all devices share, but leaves getStatus() abstract since each device 
 * type has unique status information. This enforces a consistent base while allowing specialization.
 * 
 * How do the Networked and BatteryPowered interfaces add behavior to your concrete classes?
 * These interfaces define capability contracts that devices can optionally implement. Networked 
 * provides connectivity management, while BatteryPowered handles battery state. This allows 
 * devices like DoorLock and Camera to share battery functionality without forcing Thermostat to have it.
 * 
 * Is this design an example of multiple inheritance in Java? Explain why or why not.
 * No, this is not multiple inheritance. Java supports single inheritance for classes (each concrete 
 * class extends only Device) but multiple interface implementation. Interfaces define behavior 
 * contracts without implementation inheritance, avoiding the diamond problem of true multiple inheritance.
 */