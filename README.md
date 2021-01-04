# bitEncoderDecoderCollaboration 

Shared project by: Paweł Dąbrowski, Przemysław Tyczyno and Rafał Pajdak

Multistage coding exercise based on JetBrains Academy project https://hyperskill.org/projects/58?track=1

#### Table of contents:
[What we are learning here](#whatWeLearn)<br/>
[Development progress](#developmentProgress)<br/>
[What is this project actually?](#whatItDoes)<br/>
[Workflow](#workflow)<br/>
[Braching instructions](#branching)<br/>
[Specification](#specification)<br/>

## What we are learning here<a name="whatWeLearn"></a>
<ul>
<li>working in small group on one project</li>
<li>simple documentation</li>
<li>branching and issue tracking</li>
</ul>

## Development progress:<a name ="developmentProgress"></a>
We are currently working on Stage 7.1 - refactor and clean up - deadline on 06.01.2021

## What is this project actually?<a name ="whatItDoes"></a>
We are building an App which simulates transmission of information via internet.</br> 

There are three modules here:

Name | Description
---- | -----------
Encoder | wraps an input message, using a strategy to prevent information loss
Transmitter | generates random noise in each section of transmitted message
Decoder | recovers the original input, using loss-prevention strategies

Each Stage implement another layer of an App:

Stage | Strategy | Name | Remarks
----- | -------- | ---- | -------
1 | Symbol | Encoder | duplicating chars in String
2 | Symbol | Transmitter | 
3 | Symbol | Decoder | 
4 | Symbol | Integeration | connecting stages 1-3 into an App
5 | Bitwise | Encoder | operating on bit-level in String
6 | Bitwise | Transmitter | 
7 | Bitwise | Decoder | 
7.1 | | | refactor
8 | Bitwise | Integeration | connecting stages 5-7

## Workflow<a name="workflow"></a>
<ol>
<li>Project is divided into small <strong>Stages</strong>, each has some added value to the final project. Stages are not independent, but they contribute to final stage of project.</li>
<li>Each Stage has some issues, smaller steps to accomplish - at least: tests (we try to follow TDD), documentation and implementation.</li>
<li>Issues are created on Project page in column <strong>To do</strong> and assigned to a developer.</li>
<li>When we start working on issue, we change its status from <strong>To do</strong> to <strong>In progress</strong>.</li>
<li>When the code is done, we change status to <strong>In review</strong> and wait for acceptance of other members.</li>
<li>If code is accepted, merge is done by reviewer. Change issue status to <strong>Done</strong></li>
<li>if code is not accepted, add comments and change status to <strong>In progress</strong></li>
</ol>

## Branching instruction<a name="branching"></a>
<ul><li>Each branch is named for issue number and short label, describing feature. If there is no issue connected to your task - create one.</li>
<li>We are pulling our code to <strong>dev</strong> branch.</li>
<li>After completing new stage, there is a release pull from <strong>dev</strong> to <strong>main</strong>.</li></ul>

<p>Example name of branch:</p>

> 40-newBranchesDescription

## Specification<a name="specification"></a>
#### Contents
[Stage 1 - symbol level Encoder](#stage1)<br/>
[Stage 2 - symbol level Transmitter](#stage2)<br/>
[Stage 3 - symbol level Decoder](#stage3)<br/>
[Stage 4 - App operating on implementations from Stages 1-3](#stage4)<br/>
[Stage 5 - bit level Encoder](#stage5)<br/>
[Stage 6 - bit level Transmitter](#stage6)<br/>
[Stage 7 - bit level Decoder](#stage7)<br/>

### Stage 1<a name="stage1"></a>
Introduction - create a method encode() in class Stage1. Method takes a string as an input and return another string with tripled each character.

> For example:
>
> abc -> aaabbbccc
>
> test -> ttteeesssttt

### Stage 2<a name="stage2"></a>
Create a method send() in class Stage2. Method takes a string (it should be each character repeat 3 times, for example 
instead of d - ddd) as an input and return another string, with changed one char of each of 3 chars blocks.
>For example
>
>aaabbbccc -> axabtbcoc
>
>sssooosss -> ssqsoooss

### Stage 3<a name="stage3"></a>
Create a method decode() in class Stage3. Method takes a string (as output from Stage2.send()) and tries to recover original message. Strategy is to check each section of 3 chars, because only one char per 3 is corrupted.

>For example:
>
>aaP -> a
>
>TToE_E!SSttO -> TESt

### Stage 4<a name="stage4"></a>
Connect actions from Stages 1-3 to App class. Use interfaces as described below:

> Encoder: String encode(String)
>
> Transmitter: String send(String)
>
> Decoder: String decode()

Interface implementantations come from Stages1-3.

### Stage 5<a name="stage5"></a>
Implement Encoder on bitwise level. We will work on every char of String message.<p>Please remember, that Strings are char arrays, and chars are just numbers "casted" on ASCII table values (it is a simplification but for this project should be sufficient).</p> We will manipulate every char (number) in String input. For consistency we count bits from left. Every bit of message is divided on smaller parts, like this:
> Here is sample byte of information (input):
> 
> 01011010<br/> (bits are symbolized below by letters: <br/>abcdefgh)
>
> Take 3 first bits (from left) and add 1 parity bit for verification.
>
Parity bits (on positions 7-8 from left) are loss-prevention strategy. We calculate them by adding 3 significant bits:
> 010 - odd (parity bit = 1)
>
> 000 - even (parity bit = 0)
>
> 110 - even (parity bit = 0)
>
> 111 - odd (parity bit = 1)

After this, you have 3 bits from input + 1 parity bit: abcX

> 
> Double all bits to form first byte of output:
> 
> aabbccXX
> 
> Then take next three bits from input and transfer them in similar way:
> 
> ddeeffXX
>
> And at last, the remaining bits. If there are less than 3 bits remaining, use 0 to fill blanks.
> 
> gghh00XX
>
> Your input was one byte (01011010) but output is three bytes (00110011 11110000 11000011).

### Stage 6<a name="stage6"></a>
Implement Transmitter on bitwise level. Every byte of message gets 1 of their bit (at random position) changed.

### Stage 7<a name="stage7"></a>
<p>Decoder implemented on bitwise level.</p>
<p>On input we get string send by Transmitter module. Each byte consist of paired bits. One pair of bits contain error, introduced by Transmitter.</p>

```
Example input:
00110001
01110000
11011100
```
<p>To decode original message, reduce each pair of bits to one bit:</p>

```
00 -> 0
11 -> 1
10/01 -> ? (transmission error)
```
<p>This allows you to read original message, but it may contain some errors:</p>

```
00110001 -> 010?
01110000 -> ?100
11011100 -> 1?10
```
<p>When error appears on information bits, we should use parity bit to recover missing part. Consider this formula:</p>

```
bit0 + bit1 + bit2 = bit3

Examples from previous step:
0 + 1 + 0 = ? // result is 1, but it is not essential, since this is parity bit
? + 1 + 0 = 0 // expression is true only if ? = 1, so final bits are: 1100
1 + ? + 1 = 0 // expression is true for ? = 0, so result is: 1010
```
<p>Take 3 bits of information and use them to formulate final message. Ignore parity bits, since they are only loss recovery strategy.</p>
<p>There may be surplus zero bits in last byte of message, added at encoding step - reduce them.</p>

[go to top](#top)
