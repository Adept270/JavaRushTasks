package com.javarush.task.task36.task3611;

import java.util.*;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    private Set<Integer> getFriendSet(int index) {
        Set<Integer> friendSet = new HashSet<>();
        if (index >= humanRelationships.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < humanRelationships.length; i++) {
            if (i < index && humanRelationships[index][i]) {
                friendSet.add(i);
            } else if (i > index && humanRelationships[i][index]) {
                friendSet.add(i);
            }
        }

        return friendSet;
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        //напишите тут ваш код
        Set<Integer> resultSet = new HashSet<>();
        Set<Integer> innerSet = new HashSet<>(getFriendSet(index));
        PriorityQueue<Integer> queue = new PriorityQueue<>(getFriendSet(index));

        for (int i = 0; i < deep; i++) {
            resultSet.addAll(innerSet);
            queue.addAll(innerSet);
            innerSet.clear();

            while (!queue.isEmpty()) {
                int tempFriend = queue.poll();
                innerSet.addAll(getFriendSet(tempFriend));
            }
        }
        resultSet.remove(index);
        return resultSet;
    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}