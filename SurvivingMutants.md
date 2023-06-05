# Unkillable Mutants
## AOIU_5
### The mutated code: 
```java
planes[i].location.x += planes[i].velocity * Math.cos(-planes[i].bearing / 360.0 * 2 * Math.PI );
```
### The original code: 
```java
planes[i].location.x += planes[i].velocity * Math.cos(planes[i].bearing / 360.0 * 2 * Math.PI );
```

### Reason why it's not killable
Since mathematically cos(x) = cos(-x), adding a minus sign before ```planes[i].bearing``` will have no effect on the eventual value at all. Hence, the mutated code and the original code are logically equivalent. Thus the mutated code isn't killable. 

## AOIU_6
### The mutated code: 
```java
planes[i].location.x += planes[i].velocity * Math.cos( planes[i].bearing / 360.0 * 2 * -Math.PI );
```
### The original code: 
```java
planes[i].location.x += planes[i].velocity * Math.cos( planes[i].bearing / 360.0 * 2 * Math.PI );
```

### Reason why it's not killable
Since mathematically cos(x) = cos(-x), adding a minus sign before ```Math.PI``` will have no effect on the eventual value at all. Hence, the mutated code and the original code are logically equivalent. Thus the mutated code isn't killable. 
