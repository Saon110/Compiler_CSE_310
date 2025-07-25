int i,j;
int main(){
 
	int k,ll,m,n,o,p;
 
	i = 1;
	println(i); //1
	
	j = 5 + 8;
	println(j); // 13
	
	k = i + 2*j;
	println(k); // 27

	m = k/9;
	println(m); // 3

	ll = 10;
	n = m <= ll;
	println(n); // 1

	o = i != j;
	println(o); // 1

	p = n || o;
	println(p); // 1
 
	p = n && o;
	println(p); // 1

	p++;
	println(p); // 2

	k = -p;
	println(k); // -2

	return 0;
}

