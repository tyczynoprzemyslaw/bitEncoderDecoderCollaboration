# bitEncoderDecoderCollaboration 

Shared project by: Paweł Dąbrowski and Przemysław Tyczyno

Multistage coding exercise based on JetBrains Academy project https://hyperskill.org/projects/58?track=1

## What we are learning here
<ul>
<li>working in small group on one project</li>
<li>simple documentation</li>
<li>branching and issue tracking</li>
</ul>

## Workflow
<ol>
<li>Project is divided into small <strong>Stages</strong>, each has some added value to the final project. Stages are not independent, but they contribute to final stage of project.</li>
<li>Each Stage has some issues, smaller steps to accomplish - at least: tests (we try to follow TDD), documentation and implementation.</li>
<li>Issues are created on Project page in column <strong>To do</strong> and assigned to a developer.</li>
<li>When we start working on issue, we change its status from <strong>To do</strong> to <strong>In progress</strong>.</li>
<li>When the code is done, we change status to <strong>In review</strong> and wait for acceptance of other members.</li>
<li>If code is accepted, merge is done by reviewer. Change issue status to <strong>Done</strong></li>
<li>if code is not accepted, add comments and change status to <strong>In progress</strong></li>
</ol>

## Stage 1
Introduction - create a method encode() in class Stage1. Method takes a string as an input and return another string with tripled each character.

> For example:
>
> abc -> aaabbbccc
>
> test -> ttteeesssttt
>
>## Stage 2

Create a method send() in class Stage2. Method takes a string (it should be each character repeat 3 times, for example 
instead of d - ddd) as an input and return another string, with changed one char of each of 3 chars blocks.
>For example
>
>aaabbbccc -> axabtbcoc
>
>sssooosss -> ssqsoooss