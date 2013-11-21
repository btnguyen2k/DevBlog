package bo;

import java.text.MessageFormat;

import com.github.ddth.commons.utils.HashUtils;

public class DbUtils {
    public final static String calcTableNameLinear(Object value, String template, int numBucket) {
        long bucketNum = HashUtils.linearHashingMap(value, numBucket);
        return MessageFormat.format(template, bucketNum);
    }
}
