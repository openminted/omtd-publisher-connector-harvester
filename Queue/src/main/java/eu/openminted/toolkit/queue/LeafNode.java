package eu.openminted.toolkit.queue;

/**
 *
 * @author lucasanastasiou
 */
public class LeafNode {

    String filename;
    String url;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LeafNode(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }

    @Override
    public String toString() {
        return "LeafNode{" + "filename=" + filename + ", url=" + url + '}';
    }

}
