# Air Traffic Control Language Processor
## Stockton College Software Project

The Federal Aviation Administration (FAA) Human Factors Team-Atlantic City (HFTAC) employs scientific methods and advanced technology in the conduct of research and development to ensure that systems that include human operators and maintainers perform as effectively and safely as possible.  Within the Research, Development, and Human Factors Laboratory (RDHFL), HFTAC conducts research to develop methods of measuring human performance in the aviation environments, to evaluate longer term operational concepts, and to develop useful databases and design standards through the use of high fidelity rapid prototyping and human-in-the-loop Air Traffic Control (ATC) simulation capabilities.

Realistic traffic flows and voice communications are used in the ATC simulations.  Air Traffic Controllers act as the simulation participants while pilots operate the simulated aircraft in response to ATC instructions.  The goal of this project is to create a software engine capable of interpreting transcribed ATC voice instructions, determining the type and parameters of the instruction, as well as the intended recipient.  For example, the instruction “United thirteen zero seven, climb and maintain flight level three three zero” represents an altitude clearance to FL330 (33,000 feet) for the aircraft having the call sign of UAL1307 (United Airlines flight 1307).


## Javadoc

The latest Javadoc of the edu.stockton package containing the ATCLP can be found [here](http://www.jicobaligod.com/projects/ATCLP).

## Console

Currently the Console class is used for testing purposes to call Engine methods, parse test strings, etc.

### Using the console

When you run the Console, you should see:

    ATCLP Console started
	"help" for more information
    >>
	
You can then invoke predefined methods on strings. 
Method invocation syntax is: 
	
    [method] [options] '[parameter]'
	
The _parameter_ is typically a String.
_options_ are passed like typical command line option flags.

The currently available __methods__ are:

*	__parse__ Main purpose method. Takes an ATC command string and returns an ATCCommand object containing the recognized recipient, type, and parameter. 
	For example:
		parse 'Cactus fourteen fifty descend and maintain flight level three three zero'
	The above returns an ATCCommand object. You can then retrieve the information in xml format through the object _toString_ method, which produces:
		<ATCCommand><recipient>AWE1450</recipient><type>altitude</type><parameter>FL330</parameter></ATCCommand>
*	__toNum__ Accepts a "text" number and outputs the numeric representation.
	For example:
		toNum 'thirty three hundred'
	Outputs:
		3300
*	__identify__ Accepts a telephony callsign and outputs the three-letter designator.
	For example:
		identify 'Cactus'
	Outputs:
		AWE
*	__params__ Identifies the parameters in a command string.
	For example:
		params 'Cactus fourteen fifty descend and maintain flight level three three zero'
	Outputs:
		phrase[Cactus fourteen fifty descend and maintain flight level three three zero]
		type[altitude]
		param[FL330]
*	__instruction__ Checks if the passed string is a valid instruction. Returns the index in the xml library if the passed string is valid. Returns -1 otherwise. (Essentially the _isInstruction_ method of the InstructionEngine class).
		
Methods are not case-sensitive.


__Options__:

_All options are currently implemented for the_ `parse` _method only!_

Options syntax:
Option flags are preceded by a hyphen (-), and must come before the parameter. You can precede each option individually with a hyphen, or pass a hyphen followed by multiple option flags:
	parse -f -s 'transcipt.txt'
	parse -fs 'transcript.txt'
	

*	__-v__ (verbose) Makes the LanguageProcessor output its current activities to the screen. Useful for debugging!
*	__-f__ (file input) Parses a file rather than a passed string. Filename must still be wrapped in single quotes. A sample transcript _transcript.txt_ is included for testing. __Note:__ Libraries and system is not fully implemented, so most of the transcript will not be parsed correctly.
*	__-s__ (silent) Passed with `-f` (file input) option. Suppresses any parse errors and outputs only the lines that have been successfully parsed.

### Sample ATC commands

To use the main _parse_ method of the Console/LP, you must pass a valid ATC command defined in the _instructions.xml_ library. Any passed aircraft telephonies must also exist in the _callsigns.xml_ library. Some examples are:

*	Cactus four five eight two increase speed to mach point seven two
*	United sixteen hundred maintain flight level three four zero
*	Aban eighteen o two turn left heading one hundred twenty degrees
*	Candler nine three five five reduce speed to two fifty
*	Cactus seventy thirty cleared direct lance

__Note:__ Use the above valid ATC command strings as the parameter to the _parse_ Console method. i.e. 
	parse 'Cactus seventy thirty cleared direct lance'
Which should then return a formatted XML string with the parsed parameters.

