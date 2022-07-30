# 笔记
- 递归问题的时间复杂度 Master公式
```text
形如
T(N) = a* T(N/b) + O(N^d)，其中 a、b、d都是常数 的递归函数，可以直接通过Master公式来确实时间复杂度
如果 log(a,b) < d, 时间复杂度为 O(N^d);
如果 log(a,b) > d, 时间复杂度为 O(N^log(a,b));
如果 log(a,b) == d, 时间复杂度为 O(N^d * log(N))
```
