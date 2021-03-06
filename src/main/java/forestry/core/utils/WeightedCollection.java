package forestry.core.utils;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class WeightedCollection<T> {
	private final NavigableMap<Double, T> map = new TreeMap<>();
	private double total = 0;

	public void put(double weight, T value) {
		Preconditions.checkArgument(weight > 0);
		total += weight;
		map.put(total, value);
	}

	@Nullable
	public T getRandom(Random random) {
		double value = random.nextDouble() * total;
		Map.Entry<Double, T> higherEntry = map.higherEntry(value);
		if (higherEntry == null) {
			return null;
		}
		return higherEntry.getValue();
	}

	public Set<Map.Entry<Double, T>> entrySet() {
		return Collections.unmodifiableSet(map.entrySet());
	}
}
