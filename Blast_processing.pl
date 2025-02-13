#REQUIRES: Blast_processing.pl
#TAKES
#INPUT FASTA: PRIDE.fasta
#OUTPUT FILE: output.txt 

use strict;
use warnings;
use Cwd 'abs_path';

#Get Directory that perl script is located
my $full_dir = abs_path($0);
my @split = split("/", $full_dir);
#drop the name of the perl script
pop(@split);
#turn back into directory
$full_dir = join("/", @split);
my $fasta = $full_dir."/PRIDE.fasta";

###OPEN AND BREAK FASTA INTO 60 INCREMENTS ##
open my $in, "<", "$fasta" or die "Can't open file";
my @total_fasta_lines = <$in>;
my $number_of_fasta_lines = scalar @total_fasta_lines;
my $number_of_iterations = (($number_of_fasta_lines - $number_of_fasta_lines%60)/60)+1;
my $species = $total_fasta_lines[0];
chomp($species = substr($species, 1));

open my $out, ">>", "output.txt";
print $out "$species\n";

for(my $i=0; $i<$number_of_iterations; $i++){
 	open my $fasta, ">", "input.fasta";
 	my @test = splice(@total_fasta_lines, 0, 60);
 	print $fasta "@test";
 	system "perl web_blast.pl blastp nr input.fasta > result.txt";
 	##### PROCESS RESULT ####
 	open my $result, "<", "result.txt";
 	my @total_result_lines = <$result>;
 	my @relevant_lines;
 	foreach (@total_result_lines){
 		if ($_=~ /[^Query= $species]\n/){
 			if($_=~ /$species/){
 				push @relevant_lines, $_;
 			}
 		}
 	}
 	my @proteins;
 	foreach (@relevant_lines){
 		my @split_search = split(/\|/, $_);
 		if($split_search[2]){
 			my $protein_name = $split_search[2];
 			my @split_protein_name = split(/\[/, $protein_name);
 			my $cleaned = $split_protein_name[0];
 			push @proteins, $cleaned;
 		}
 	}
 	#print "@proteins";
 	#print scalar @proteins;

	foreach(@proteins){
		print $out "$_\n";
	}
 }
system "awk '!a[$0]++' output.txt > clean_output.txt";
