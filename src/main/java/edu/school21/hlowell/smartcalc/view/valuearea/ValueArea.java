package edu.school21.hlowell.smartcalc.view.valuearea;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValueArea {
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private double step;
}
