## Bioinformatics

#### Longest Common Subsequence Between Two DNA Strings

![alt text](https://github.com/AustinMoninger/programming-practice-2017/blob/master/images/Sequence.png)

A common problem in molecular biology is that of sequence alignment. Given DNA
sequences from two different organisms, biologists often want to match up similar portions of
those sequences in order to focus on the portions that are different. Such alignment helps
biologists determine which bases in the sequence would need to be changed in order to
transform one into another, which then allows biologists to assess how closely related two
different organisms are.

#### Minimal Energy Folding of an RNA String

The RNA string can actually fold in on itself so that a base on one part of the RNA
string pairs with a complementary base on another part of the string. RNA will typically favor a
shape that is in a minimal energy state. This simply means that a maximal number of base
pairings occur (any unpaired bases in the string increase its overall energy). However, there are
geometric constraints on which bases can pair off.


## Process Scheduler

![alt text](https://github.com/AustinMoninger/programming-practice-2017/blob/master/images/Scheduler.png)

Program that simulates a process scheduler for a system with a single processor; outputs when and for how long each process is using the processor.


## Animal Decision Tree

![alt text](https://github.com/AustinMoninger/programming-practice-2017/blob/master/images/DecisionTree.png)

This decision tree can be used for an animal guessing game. The user first thinks of an animal, such as a horse, and then answers the questions in the tree: Is a horse a mammal? Yes, so go down the right branch. Does a horse have four legs? Yes, so go down the right branch. Does a horse have claws? No, so go down the left branch. Is the animal a horse? Indeed it is! The program has correctly identified the animal, and would be able to do so for tigers, dolphins, ants, and spiders as well.

Of course, there are more animals than the small set defined in this tree, so the program learns from the user whenever a final answer is reached that is incorrect. For example, if the animal I had in mind were a honey bee, then I would say no to the animal being a mammal, yes to it being an insect, then no to it being an ant. At this point, the program should ask me to provide a yes-or-no question that would allow it to distinguish an ant from a honey bee. I could type, “Is the animal a pollinator?”, and then indicate that honey bees are pollinators but ants are not. The program would incorporate this information into the tree, so that it would get this answer right the next time it comes up. Thus, the program builds a large knowledge base to load from at the start of each run.
