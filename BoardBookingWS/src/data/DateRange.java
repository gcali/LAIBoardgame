package data;

import java.time.LocalDate;

public class DateRange {
    
    private final LocalDate base;
    private final LocalDate end;

    public DateRange(LocalDate base, LocalDate end) {
        if (base == null || end == null) {
            throw new IllegalArgumentException("DateRange doesn't accept null values");
        }
        this.base = base;
        this.end = end;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof DateRange)) {
            return false;
        } else {
            DateRange other = (DateRange) obj;
            return base.equals(other.base) && end.equals(other.end);
        }
    }
    
    @Override
    public int hashCode() {
        return base.hashCode() ^ end.hashCode();
    }
    
    public boolean overlaps(DateRange other) {
        return base.isBefore(other.end) && end.isAfter(other.base);
    }

    public DateRange getOverlap(DateRange other) {
        if (!this.overlaps(other)) {
            return null;
        } else {
            LocalDate overlapBase;
            if (base.isAfter(other.base)) {
                overlapBase = base;
            } else {
                overlapBase = other.base;
            }
            LocalDate overlapEnd;
            if (end.isBefore(other.end)) {
                overlapEnd = end;
            } else {
                overlapEnd = other.end;
            }
            
            return new DateRange(overlapBase, overlapEnd);
        }
    }
    
    public boolean isDateIn(LocalDate date) {
        return !(base.isAfter(date)) && end.isAfter(date);
    }

    public LocalDate getBase() {
        return base;
    }

    public LocalDate getEnd() {
        return end;
    }
}
