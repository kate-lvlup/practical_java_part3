package jom.com.softserve.s3.task5;

enum ClientType {
    NEW(1) {
        @Override
        public double discount() {
            return 1.0;
        }
    },
    SILVER(12) {
        @Override
        public double discount() {
            return (100 - 12 * 0.25) / 100.0;
        }
    },
    GOLD(30) {
        @Override
        public double discount() {
            return (100 - 30 * 0.3) / 100.0;
        }
    },
    PLATINUM(60) {
        @Override
        public double discount() {
            return (100 - 60 * 0.35) / 100.0;
        }
    };

    private final int months;

    ClientType(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public double discount() {
        return 1.0;
    }
}


