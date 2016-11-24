package eu.openminted.toolkit.queue.services;

import eu.openminted.toolkit.queue.LeafNode;

/**
 *
 * @author lucasanastasiou
 */
public interface QueueService {
    
    public void pushLeafNode(LeafNode leafNode);
    
    public LeafNode getLeafNode();
}
