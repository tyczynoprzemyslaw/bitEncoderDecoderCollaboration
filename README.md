# bitEncoderDecoderCollaboration<a name="top"></a>
Shared project by: Paweł Dąbrowski, Przemysław Tyczyno and Rafał Pajdak

Multistage coding exercise based on JetBrains Academy project https://hyperskill.org/projects/58?track=1

#### Contents:
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
We are currently working on releasing functioning Stage 5 on 16.12.2020.

## What is this project actually?<a name ="whatItDoes"></a>
We are building an App which simulates transmission of information via internet.</br> 
There are three modules here:
<ol>
<li><strong>Encoder</strong> - encodes input using a specific strategy to prevent information loss on transfer.</li>
<li><strong>Transmitter</strong> - simulates transmission errors occurring while sending a message, i.e. generates random noise in each section of input.</li>
<li><strong>Decoder</strong> - reads message with errors and recovers the original input, using loss-prevention strategies implemented while encoding message.</li></ol>
<p>On Stages 1-4 we are working with loss prevention strategies based on symbol (char) level.<br/>Stages 5+ will be dedicated for bitwise operations, such as bit parity or/and Hamming code.</p>

 ![App schema](images/bitEncoderSchemat.jpg)

## Workflow<a name="workflow"></a>
<ol>
<li>Project is divided into small <strong>Stages</strong>, each contributing to the final project. Stages are independent, but we are releasing them in ascending order.</li>
<li>Each Stage consist of smaller steps to accomplish - at least: tests (we try to follow TDD), documentation and implementation.</li>
<li>Issues are created on Project page in column <strong>To do</strong> and assigned to a developer. Every contribution should have an issue created.</li>
<li>When we start working on issue, we change its status from <strong>To do</strong> to <strong>In progress</strong>.</li>
<li>When the code is done, we change status to <strong>In review</strong> and wait for acceptance of other members.</li>
<li>If code is accepted, merge is done by contributor. Change issue status to <strong>Done</strong></li>
<li>if code is not accepted, add comments and change status to <strong>In progress</strong></li>
</ol>

## Branching instruction<a name="branching"></a>
<ul><li>Each branch is named for issue number and short label, describing feature. If there is no issue connected to your task - create one.</li>
<li>We are pulling our code to <strong>dev</strong> branch for <strong>current Stage</strong> PRs.</li>
<li>While working on features for future Stages (not considered in nearest release), we are pulling to <strong>dev-StageX</strong> branches respectively.</li>
<li>After completing new stage, there is a release pull from <strong>dev</strong> to <strong>main</strong>. It is done during team meeting after discussion.</li></ul>

<p>Example name of branch:</p>

> 40-newBranchesDescription
> 

## Specification<a name="specification"></a>
#### Contents
[Stage 1 - symbol level Encoder](#stage1)<br/>
[Stage 2 - symbol level Transmitter](#stage2)<br/>
[Stage 3 - symbol level Decoder](#stage3)<br/>
[Stage 4 - App operating on implementations from Stages 1-3](#stage4)<br/>

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
> Decoder: String decode(String)

Interface implementantations come from Stages1-3.
<p></p>

[go to top](#top)
