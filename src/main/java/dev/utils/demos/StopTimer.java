package dev.utils.demos;

/**
 * 秒表计时器
 */
class StopTimer {
    private long startT;

    private long endT;

    public void start() {
        this.startT = System.currentTimeMillis();
    }

    public void stop() {
        this.endT = System.currentTimeMillis();
    }

    public String getTotalTimeMillis() {
        return String.valueOf(endT - startT);
    }
}
