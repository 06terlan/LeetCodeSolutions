package java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 * 
 * @see https://leetcode.com/problems/cheapest-flights-within-k-stops
 */
public class CheapestFlightsWithinKStops {
/**
     * Bellman Ford agloright used
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // represent the destinations that needs to be visited next
        final Queue<Info> next = new LinkedList<>();
        //final Map<Integer, List<int[]>> dest = new HashMap<>();
        List<Info>[] dest = new ArrayList[n];
        boolean found = false;
        int result = Integer.MAX_VALUE, size, level = k + 2;
        int[] minToNode = new int[n];

        // create source to destination mapping with weight
        for (final int[] flight: flights) {
            if (dest[flight[0]] == null) {
                dest[flight[0]] = new ArrayList<>();
            }

            dest[flight[0]].add(new Info(flight[0], flight[1], flight[2]));
        }

        // add the initial source
        // every element in that queue will be {current source, accumulated price, curret k value}
        // initially k and price will be 0 and as we move these values will be used for calcualtiong the next {k} and {price} for the destination
        next.add(new Info(src, 0, 0));
        while ((size = next.size()) > 0 && level-- > 0) {

            for (int i=0;i<size;i++) {
                // poll the next destination from the queue
                final Info curPos = next.poll();
                minToNode[curPos.source] = curPos.weight;

                // check if we already get to the destination 
                // if yes then calculate the minimum price
                // else add next destinations to the queue
                // also mark found as true since that indicates if we ever find a flight, that will decide the final result
                if (curPos.source == dst) {
                    found = true;
                    result = Math.min(result, curPos.weight);
                } else if (dest[curPos.source] != null) {
                    // add the next destinations to the queue
                    for (final Info nextDestination: dest[curPos.source]) {
                        if (minToNode[nextDestination.destination] == 0 || minToNode[nextDestination.destination] > curPos.weight + nextDestination.weight) {
                            next.add(new Info(nextDestination.destination, 0, curPos.weight + nextDestination.weight));
                        }
                    }
                }
            }
        }

        return found ? result : -1;
    }

    class Info {
        public Integer source;
        public Integer destination;
        public Integer weight;
        
        public Info(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}
