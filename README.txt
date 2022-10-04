## This is the rough format for the .jssppsmm files:
=====
ORDERING STACK|QUEUE
NUMBER_LENGTH lengthOfNumberValues extraDigitsDuringProcessing

NODE type node
CONNECTION node0 outputSocket inputSocket node1
INPUT node inputSocket value
OUTPUT node outputSocket value
POSITION node x y w h
COLOR node r g b a
=====

## This is how to format values:
=====
NUMBER:0.0
TEXT:[Hello, World!]
MULTI:(NUMBER:42,TEXT:[You can put brackets in text by escaping: \[\]])
PROGRAM_LINK:{path/to/your/program}
-----
PROGRAM_CODE:{
ORDERING ...
NUMBER_LENGTH ...

NODE ...
...
}
=====

## Each Node's input sockets and output sockets (comprehensive docs for functionality will be elsewhere.)
=====
Accumulator         : value ready empty                     : value
Add                 : value0 value1                         : value
And                 : value0 value1                         : value
Buffer              : value ready                           : value
Divide              : value0 value1                         : value
Embedded is unique, and its inputs and outputs depend on the program provided. Each EmbedInput & EmbedOutput corresponds
    to an input or output. The one input that is always there and doesn't correspond to an EmbedInput is        program.
EmbedInput          :                                       : value
EmbedOutput         : value                                 :
EmbedStartPoint     :                                       : value
Equals              : value0 value1                         : value
FirstAndRest        : value                                 : first rest done
GreaterThan         : value0 value1                         : value
Group               : value0 value1                         : value
Identity            : value                                 : value
If                  : value                                 : true false
InputNumber         :                                       : value
InputText           :                                       : value
LastAndRest         : value                                 : last rest done
LessThan            : value0 value1                         : value
MultiConcat         : value0 value1                         : value
MultiLength         : value                                 : value
MultiOfCharactersToText : value                             : value
Multiply            : value0 value1                         : value
NonUpdater          : value                                 : value
Not                 : value                                 : value
Or                  : value0 value1                         : value
Print               : value                                 :
Select              : value index                           : value
SelectRepeat        : value index                           : value
Subtract            : value0 value1                         : value
TextConcat          : value0 value1                         : value
TextLength          : value                                 : value
TextToMultiOfCharacters : value                             : value
ToNumber            : value                                 : value
ToText              : value                                 : value
Xor                 : value0 value1                         : value
=====