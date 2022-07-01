# coding 01
- 对于一个数N，~N+1 = -N
- 对于一个int数字，负的部分会大于正的部分1个，因为正和0的最左边都是0，而负的最左边是1，负数独享全部空间
- 数据结构大类分两种，数组 和 链表
- 数组：地址连续，线性地址寻址快（当数组特别大时可能物理地址会分多段，不连续）
# coding 02 
- 对数器
- 随机数的产生（由已经的任意随机函数(相等或不等概率)，获得另外的随机函数, ）
# coding 03
- 二分法变种：二分法不一定要有序
- 时间复杂度:
- 二分法是O(logN)
- 冒泡、选择排序 O(N^2) 即等差数量
- arrayList扩容代价是 O(N),均摊下来 0(N)/N = O(1)
- 因为每次扩容都需要复制之前的所有数据 代价是 已存在的数据量，所以到最后的代价总和是等比数列求和（1+2+4+8+...+N）
- 等比数列求和   a1*((1-q^n)/(1-q)), n=logN(因为扩容次数为接近到N的二次幂位置),而且log2(N)和log9(N)可以看成一样
- a1*((1-q^n)/(1-q)) = a1*((1-q^(logN))/(1-q)) = a1*((1-N)/(1-q)) = a1*((1/1-q)-(N/1-q)),
- 时间复杂度是 O(N),均摊到N个数，每个数的代码是O(1)