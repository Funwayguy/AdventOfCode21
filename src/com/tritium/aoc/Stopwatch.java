package com.tritium.aoc;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.*;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

// Standalone version of Google's Stopwatch
public class Stopwatch {
    private final Supplier<Long> nanoTimer;
    private boolean isRunning = false;
    private long elapsedTime = 0L;
    private long startTime = 0L;
    
    public Stopwatch()
    {
        this(System::nanoTime);
    }
    
    public Stopwatch(Supplier<Long> nanoTimeSrc)
    {
        this.nanoTimer = nanoTimeSrc;
    }
    
    public Stopwatch start()
    {
        if(isRunning) throw new IllegalStateException("Stopwatch already running!");
        isRunning = true;
        startTime = nanoTimer.get();
        return this;
    }
    
    public Stopwatch stop()
    {
        if(!isRunning) throw new IllegalStateException("Stopwatch already stopped!");
        elapsedTime += nanoTimer.get() - startTime;
        isRunning = false;
        return this;
    }
    
    public boolean isRunning()
    {
        return this.isRunning;
    }
    
    public Stopwatch reset()
    {
        isRunning = false;
        elapsedTime = 0L;
        return this;
    }
    
    public final long elapsedNano()
    {
        return isRunning ? (nanoTimer.get() - startTime + elapsedTime) : elapsedTime;
    }
    
    public long elapsed(TimeUnit timeUnit)
    {
        return timeUnit.convert(elapsedNano(), NANOSECONDS);
    }
    
    @Override
    public String toString()
    {
        long nano = elapsedNano();
        TimeUnit unit = chooseUnit(nano);
        return String.format(Locale.ROOT, "%.4g", (double)nano / NANOSECONDS.convert(1, unit)) + " " + abbreviate(unit);
    }
    
    private static TimeUnit chooseUnit(long nanos)
    {
        if(DAYS.convert(nanos, NANOSECONDS) > 0) return DAYS;
        if(HOURS.convert(nanos, NANOSECONDS) > 0) return HOURS;
        if(MINUTES.convert(nanos, NANOSECONDS) > 0) return MINUTES;
        if(SECONDS.convert(nanos, NANOSECONDS) > 0) return SECONDS;
        if(MILLISECONDS.convert(nanos, NANOSECONDS) > 0) return MILLISECONDS;
        if(MICROSECONDS.convert(nanos, NANOSECONDS) > 0) return MICROSECONDS;
        return NANOSECONDS;
    }
    
    private static String abbreviate(TimeUnit unit)
    {
        switch(unit)
        {
            case NANOSECONDS: return "ns";
            case MICROSECONDS: return "\u03bcs"; // μs
            case MILLISECONDS: return "ms";
            case SECONDS: return "s";
            case MINUTES: return "min";
            case HOURS: return "h";
            case DAYS: return "d";
            default: throw new AssertionError();
        }
    }
}
