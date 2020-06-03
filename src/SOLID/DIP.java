package SOLID;

/* Dependency Inversion : high-level modules should not depend on low-level modules.
   Both should depend on abstractions (i.e. interface)
 */

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

import javax.management.relation.Relation;

public class DIP {

    public static void main(String[] args){
        Person ben = new Person("ben");
        Person pei = new Person("pei");
        Relationships r = new Relationships();
        r.addParentAndChild(pei, ben);
        new Research(r);
    }
}

enum Relationship
{
    SIBLING, CHILD, PARENT
}

class Person
{
    public String name;
    public Person(String name) {
        this.name = name;
    }
}

class Relationships implements RelationshipBrowser // low-level
{
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child)
    {
        relations.add(new Triplet(parent, Relationship.PARENT, child));
        relations.add(new Triplet(child, Relationship.CHILD, parent));
    }

//    public List<Triplet<Person, Relationship, Person>> getRelations() {
//        // this should not be exposed publicly
//        return relations;
//    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream().filter(p -> p.getValue0().name.equals(name)
                && p.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

class Research // high-level
{

//    public Research(Relationships relationships)
//    {
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(x -> x.getValue1() == Relationship.PARENT
//                    && x.getValue0().name.equals("pei"))
//                .forEach(ch -> System.out.println("pei has a child " + ch.getValue2().name));
//    }

    public Research(RelationshipBrowser browser)
    {
        List<Person> peiChildren = browser.findAllChildrenOf("pei");
        for (Person child : peiChildren)
        {
            System.out.println(child.name);
        }
    }
}

interface RelationshipBrowser
{
    List<Person> findAllChildrenOf(String name);
}