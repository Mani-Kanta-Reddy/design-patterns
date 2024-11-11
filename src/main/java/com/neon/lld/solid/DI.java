package com.neon.lld.solid;

import java.util.ArrayList;
import java.util.List;

public class DI {
    public static void main(String[] args) {
        Person john = new Person("John");
        Person mik = new Person("Mik");
        Person jane = new Person("Jane");
        RelationShip relationShip = new RelationShip();
        relationShip.addParentAndChild(john, mik);
        relationShip.addParentAndChild(john, jane);
        relationShip.addSibling(mik, jane);

        System.out.println(relationShip.findAllChildrenOf(john));
    }
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               '}';
    }
}

enum Relation {
    PARENT,
    CHILD,
    SIBLING
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(Person parent);
    List<Person> findSiblingsOf(Person person);
}

// Low - Level Module
class RelationShip implements RelationshipBrowser {
    List<Triplet> relationships = new ArrayList<>();
    public void addParentAndChild(Person parent, Person child) {
        relationships.add(new Triplet(parent, Relation.PARENT, child));
        relationships.add(new Triplet(child, Relation.CHILD, parent));
    }

    public void addSibling(Person person1, Person person2) {
        relationships.add(new Triplet(person1, Relation.SIBLING, person2));
        relationships.add(new Triplet(person2, Relation.SIBLING, person1));
    }

    public List<Person> findAllChildrenOf(Person parent) {
        return relationships.stream()
                .filter(relation -> relation.person1 == parent && relation.relation == Relation.PARENT)
                .map(triplet -> triplet.person2)
                .toList();
    }

    public List<Person> findSiblingsOf(Person person) {
        return relationships.stream()
                .filter(relationTriplet -> relationTriplet.person1 == person && relationTriplet.relation == Relation.SIBLING)
                .map(triplet -> triplet.person2)
                .toList();
    }
}

// High - Level Module
class Research {

    RelationshipBrowser relationshipBrowser;

    public Research(RelationshipBrowser relationshipBrowser) {
        this.relationshipBrowser = relationshipBrowser;
    }

    public int findNoOfChildrenFor(Person parent) {
        return relationshipBrowser.findAllChildrenOf(parent).size();
    }

    public int findAvgAgeDiffBWAllSiblings(List<Person> personList) {
        // TO BE Implemented
        return -1;
    }
}

class Triplet {
    Person person1;
    Relation relation;
    Person person2;

    public Triplet(Person person1, Relation relation, Person person2) {
        this.person1 = person1;
        this.relation = relation;
        this.person2 = person2;
    }
}

