//package com.arc.test.test2;
//
///**
// * @author may
// * @since 2019/6/20 0:32
// */
//
//public enum TrafficLamp {
//    RED(30) {
//        @Override
//        public TrafficLamp nextLamp() {
//            return GREEN;
//        }
//    },
//    GREEN(20) {
//        @Override
//        public TrafficLamp nextLamp() {
//            return YELLOW;
//        }
//    },
//    YELLOW(10) {
//        @Override
//        public TrafficLamp nextLamp() {
//            return RED;
//        }
//    };
//
//    public abstract TrafficLamp nextLamp();
//
//    private int time;
//
//    private TrafficLamp(int time) {
//        this.time = time;
//        System.out.println(time);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(TrafficLamp.GREEN);
//    }
//}
