rm -rf *TEST*

cd ../bin/
java org/ucm/tp1/BuffyVampireSlayer easy 5 < ../test_cases/easy_s5_0.txt > ../test_cases/easy_s5_0_output_TEST.txt
diff -q ../test_cases/easy_s5_0_output.txt ../test_cases/easy_s5_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "easy_s5_0 is identical"
else
  echo "easy_s5_0 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer easy 5 < ../test_cases/easy_s5_1.txt > ../test_cases/easy_s5_1_output_TEST.txt
diff -q ../test_cases/easy_s5_1_output.txt ../test_cases/easy_s5_1_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "easy_s5_1 is identical"
else
  echo "easy_s5_1 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer easy 5 < ../test_cases/easy_s5_2.txt > ../test_cases/easy_s5_2_output_TEST.txt
diff -q ../test_cases/easy_s5_2_output.txt ../test_cases/easy_s5_2_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "easy_s5_2 is identical"
else
  echo "easy_s5_2 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer easy 5 < ../test_cases/easy_s5_3.txt > ../test_cases/easy_s5_3_output_TEST.txt
diff -q ../test_cases/easy_s5_3_output.txt ../test_cases/easy_s5_3_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "easy_s5_3 is identical"
else
  echo "easy_s5_3 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer hard 1 < ../test_cases/hard_s1_0.txt > ../test_cases/hard_s1_0_output_TEST.txt
diff -q ../test_cases/hard_s1_0_output.txt ../test_cases/hard_s1_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "hard_s1_0 is identical"
else
  echo "hard_s1_0 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer hard 1 < ../test_cases/hard_s1_1.txt > ../test_cases/hard_s1_1_output_TEST.txt
diff -q ../test_cases/hard_s1_1_output.txt ../test_cases/hard_s1_1_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "hard_s1_1 is identical"
else
  echo "hard_s1_1 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer insane 5 < ../test_cases/insane_s5_0.txt > ../test_cases/insane_s5_0_output_TEST.txt
diff -q ../test_cases/insane_s5_0_output.txt ../test_cases/insane_s5_0_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "insane_s5 is identical"
else
  echo "insane_s5 is not identical"
fi

java org/ucm/tp1/BuffyVampireSlayer insane 1 < ../test_cases/insane_s5_1.txt > ../test_cases/insane_s5_1_output_TEST.txt
diff -q ../test_cases/insane_s5_1_output.txt ../test_cases/insane_s5_1_output_TEST.txt 1>/dev/null
if [[ $? == "0" ]]
then
  echo "insane_s5 is identical"
else
  echo "insane_s5 is not identical"
fi
