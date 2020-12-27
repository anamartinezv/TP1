rm -rf *TEST*

cd ../bin/
java org/ucm/tp1/BuffyVampireSlayer  easy 1 < ../test_cases/easy_s1_0.txt > ../test_cases/easy_s1_0_output_TEST.txt
diff -q ../test_cases/easy_s1_0_output.txt ../test_cases/easy_s1_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo " easy_s1_0 is identical"
else
  echo " easy_s1_0 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer  easy 1 < ../test_cases/easy_s1_1.txt > ../test_cases/easy_s1_1_output_TEST.txt
diff -q ../test_cases/easy_s1_1_output.txt ../test_cases/easy_s1_1_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo " easy_s1_1 is identical"
else
  echo " easy_s1_1 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer  easy 1 < ../test_cases/easy_s1_2.txt > ../test_cases/easy_s1_2_output_TEST.txt
diff -q ../test_cases/easy_s1_2_output.txt ../test_cases/easy_s1_2_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo " easy_s1_2 is identical"
else
  echo " easy_s1_2 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer  easy 2 < ../test_cases/easy_s2_0.txt > ../test_cases/easy_s2_0_output_TEST.txt
diff -q ../test_cases/easy_s2_0_output.txt ../test_cases/easy_s2_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo " easy_s2_0 is identical"
else
  echo " easy_s2_0 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer hard 1 < ../test_cases/hard_s1_0.txt > ../test_cases/hard_s1_0_output_TEST.txt
diff -q ../test_cases/hard_s1_0_output.txt ../test_cases/hard_s1_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "hard_s1_0 is identical"
else
  echo "hard_s1_0 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer hard 2 < ../test_cases/hard_s2_0.txt > ../test_cases/hard_s2_0_output_TEST.txt
diff -q ../test_cases/hard_s2_0_output.txt ../test_cases/hard_s2_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "hard_s2_0 is identical"
else
  echo "hard_s2_0 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer insane 1 < ../test_cases/insane_s1_0.txt > ../test_cases/insane_s1_0_output_TEST.txt
diff -q ../test_cases/insane_s1_0_output.txt ../test_cases/insane_s1_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "insane_s1 is identical"
else
  echo "insane_s1 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer insane 2 < ../test_cases/insane_s2_0.txt > ../test_cases/insane_s2_0_output_TEST.txt
diff -q ../test_cases/insane_s2_0_output.txt ../test_cases/insane_s2_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "insane_s2 is identical"
else
  echo "insane_s2 is not identical"
fi
