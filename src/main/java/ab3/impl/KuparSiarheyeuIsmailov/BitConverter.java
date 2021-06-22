package ab3.impl.KuparSiarheyeuIsmailov;

public class BitConverter {

    private final int bitSize;

    public BitConverter(int bitSize){
        this.bitSize = bitSize;
    }



    public long[] convertToBitSize(byte[] data){
        boolean[] binaryData = new boolean[data.length * 8];

        int nextBitIndex = 0;

        for(byte date : data){
            for (int i = 0; i < 8; i++) {
                binaryData[nextBitIndex++] = getKthBit(date, 7 - i);

            }
        }
        int nWords = (int)Math.ceil(binaryData.length / (float)bitSize);

        long[] result = new long[nWords];

        for (int i = 0; i < nWords; i++) {
            long word = 0;

            for (int j = 0; j < bitSize; j++) {
                int bitIndex = i * bitSize + j;

                long bit = binaryData[bitIndex] ? 1 : 0;


                long mask = bit << ((bitSize - 1) - j);

                word = word | mask;
            }
            result[i] = word;
        }

        return result;
    }
    private boolean getKthBit(byte b, int k){

        byte mask = (byte) (1 << k);

        byte value = (byte) (mask & b);

        byte result = (byte) (value >> k);

        return result == 1;
    }

    private boolean getKthBit(long l, int k){
        long mask = (1L << k);

        long value = (mask & l);

        long result = (value >> k);

        return result == 1;
    }

    public byte[] convertFromBitSize(long[] data){
        boolean[] binaryData = new boolean[data.length * bitSize];

        int nextBitIndex = 0;

        for(long date : data){
            for (int i = 0; i < bitSize; i++) {
                binaryData[nextBitIndex++] = getKthBit(date, (bitSize - 1) - i);

            }
        }
        byte[] result = new byte[binaryData.length / 8];

        for (int i = 0; i < result.length; i++) {
            byte word = 0;

            for (int j = 0; j < 8; j++) {
                int bitIndex = i * 8 + j;

                byte bit = (byte) (binaryData[bitIndex] ? 1 : 0);


                byte mask = (byte)(bit << (7 - j));

                word = (byte) (word | mask);
            }

            result[i] = word;
        }
        return result;
    }
}
