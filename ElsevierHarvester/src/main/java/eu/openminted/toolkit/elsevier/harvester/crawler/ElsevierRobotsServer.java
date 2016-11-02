package eu.openminted.toolkit.elsevier.harvester.crawler;

import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 *
 * @author lucasanastasiou
 */
public class ElsevierRobotsServer extends RobotstxtServer{

    public ElsevierRobotsServer(){
        super(null,null);
    }
    
    @Override
    public boolean allows(WebURL webURL){
        return true;
    }
}
