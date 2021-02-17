package com.arc.test.random.test1;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public  class Node {

    int low;//下限(含)
    int high;//上限(不含)
    float weight;//权重

    public Node(int low, int high, float weight) {
        this.low = low;
        this.high = high;
        this.weight = weight;
    }

    public Node(float weight) {
        this.low = 0;
        this.high = (int) (100 * weight);
    }
}
