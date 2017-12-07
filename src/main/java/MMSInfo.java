/**
 * Created by yuxiao on 12/7/17.
 */
public final class MMSInfo {
    private final String deviceID;
    private final String url;

    private final int maxAttachmentSizeInBytes;

    public MMSInfo(String deviceID,String url, int maxAttachmentSizeInBytes){
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public MMSInfo(MMSInfo prototype){
        this.deviceID = prototype.deviceID;
        this.url = prototype.url;
        this.maxAttachmentSizeInBytes = prototype.maxAttachmentSizeInBytes;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInBytes() {
        return maxAttachmentSizeInBytes;
    }
}
